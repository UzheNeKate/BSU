//
// Created by 1111 on 28.10.21.
//

#include <fstream>
#include <string>
#include <list>
#include <pthread.h>
#include <algorithm>
#include <iostream>
#include <chrono>

#define p 6
#define n 2000
#define wordToFind "hello"

using namespace std;

string *textFiles;

bool full = false;
int tasksLeft = n;
pthread_mutex_t mx;
pthread_cond_t cond;


struct PThreadPortion {
    string textFile;
    list<string> wordsFromText;
};

PThreadPortion portion;


void *ThreadFunctionProducer(void *pvParam) {
    string word;
    list<string> wordsFromText;

    for (int i = 0; i < n; i++) {
        ifstream input_file(textFiles[i]);
        while (input_file >> word)
            wordsFromText.push_back(word);
        input_file.close();


        pthread_mutex_lock(&mx);
        while (full) {
            pthread_cond_wait(&cond, &mx);
        }
        portion.textFile = textFiles[i];
        portion.wordsFromText = wordsFromText;
        full = true;
        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&mx);

        wordsFromText.clear();
    }
    return nullptr;
}

void *ThreadFunctionConsumer(void *pvParam) {

    auto searchWord = (string*) pvParam;
    list<string> wordsFromText;
    string word;
    string textName;
    bool busy = true;
    while(true) {
        pthread_mutex_lock(&mx);
        while (!full) {
            if (tasksLeft == 0) {
                busy = false;
                break;
            }
            pthread_cond_wait(&cond, &mx);
        }

        if (!busy) {
            pthread_cond_signal(&cond);
            pthread_mutex_unlock(&mx);
            break;
        }

        wordsFromText = portion.wordsFromText;
        textName = portion.textFile;
        full = false;
        tasksLeft--;

        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&mx);

        auto cnt = std::count(wordsFromText.begin(), wordsFromText.end(), *searchWord);

        //printf("File: %s\t%d\n", textName.c_str(),cnt);
    }
    return nullptr;
}


int main() {
    textFiles = new string[n];
    pthread_t hThreads[p];
    pthread_mutex_init(&mx, nullptr);
    pthread_cond_init(&cond, nullptr);

    for (int i = 0; i < n; ++i) {
        textFiles[i] = ".\\files\\" + to_string(i) + ".txt";
    }

    auto beginTime = chrono::steady_clock::now();
    pthread_create(&hThreads[0], nullptr, ThreadFunctionProducer, nullptr);
    for (int k = 1; k < p; ++k) {
        string word = wordToFind;
        pthread_create(&hThreads[k], nullptr, ThreadFunctionConsumer, &word);
    }

    for (auto hThread : hThreads)
        pthread_join(hThread, nullptr);

    auto endTime = chrono::steady_clock::now();
    printf("Poducer-consumer time: %d [microsec]\n",
           chrono::duration_cast<chrono::microseconds>(endTime - beginTime).count());

    pthread_mutex_destroy(&mx);
    pthread_cond_destroy(&cond);

    delete[] textFiles;
}
