//
// Created by 1111 on 27.10.21.
//

#include "parallel_p2p_program.h"
#include "common.h"

#include <iostream>
#include <fstream>
#include <map>
#include <pthread.h>
#include <queue>
#include <cmath>
#include <chrono>

using namespace std;

struct FindInfo {
    FindInfo(const vector<string> &fileNames, const string &desiredWord, int first, int last, map<string, int> &result)
            : desiredWord(desiredWord), fileNames(fileNames), result(result) {
        this->first = first;
        this->last = last;
    }

    const vector<string> &fileNames;
    int first;
    int last;
    const string &desiredWord;
    map<string, int> &result;
};

void* findInFilesP2P(void *args) {
    auto* info = (FindInfo*) args;
    for (int i = info->first; i < info->last; i++) {
        int wordsCount = findInFile(info->desiredWord, info->fileNames[i]);
        info->result[info->fileNames[i]] = wordsCount;
    }
    return nullptr;
}

void startFindThreads(const string &desiredWord, const vector<string> &fileNames, map<string, int> &result,
                      int threadsCount) {
    int sliceSize = ceil((double) fileNames.size() / (double) threadsCount);
    vector<pthread_t> threads(threadsCount);
    vector<FindInfo *> args(threadsCount);
    for (int i = 0; i < threadsCount; i++) {
        args[i] = new FindInfo(fileNames, desiredWord, i * sliceSize,
                               min((i + 1) * sliceSize, (int) fileNames.size()),result);
    }
    auto beginTime = chrono::steady_clock::now();
    for (int i = 0; i < threads.size(); i++) {
        pthread_create(&threads[i], nullptr, findInFilesP2P, args[i]);
    }
    for (pthread_t thread: threads) {
        pthread_join(thread, nullptr);
    }
    auto endTime = chrono::steady_clock::now();
    printf("Parallel elapsed time: %d [microsec]\n",
           chrono::duration_cast<chrono::microseconds>(endTime - beginTime).count());
}

void parallelProgram(std::string &desiredWord, std::vector<std::string> &fileNames, int threadsCount) {
    map<string, int> result;
    startFindThreads(desiredWord, fileNames, result, threadsCount);
    //printResult(result);
}