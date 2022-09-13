#include <iostream>
#include <vector>
#include <fstream>
#include <list>
#include "seq_program.h"
#include "parallel_p2p_program.h"

using namespace std;

void generateFiles(int n, vector<string> &fileNames) {
    for (int i = 0; i < n; ++i) {
        string fileName = ".\\files\\" + to_string(i) + ".txt";
//        ofstream fout(fileName);
//        for (int j = 0; j < n; j++) {
//            fout << (j < i ? "Hello " : "hello ");
//        }
//        fout.close();
        fileNames.push_back(fileName);
    }
}

int main() {
    string desiredWord = "hello";
    vector<string> fileNames;
    generateFiles(2000, fileNames);
    for (int i = 1; i < 10; i++) {
        //parallelProgram(desiredWord, fileNames, 2);
        sequenceProgram(desiredWord, fileNames);
    }
    //producerConsumerProgram(desiredWord, fileNames, );
    return 0;
}


