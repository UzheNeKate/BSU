//
// Created by 1111 on 27.10.21.
//

#include "seq_program.h"
#include "common.h"
#include <iostream>
#include <fstream>
#include <map>
#include <chrono>

using namespace std;

void findInFiles(const string &desiredWord, const vector<string> &fileNames, map<string, int>& result) {
    for (auto & fileName : fileNames) {
        int wordsCount = findInFile(desiredWord, fileName);
        result[fileName] = wordsCount;
    }
}

void sequenceProgram(std::string& desiredWord, std::vector<std::string>&fileNames) {
    map<string, int> result;
    auto beginTime = chrono::steady_clock::now();
    findInFiles(desiredWord, fileNames, result);
    auto endTime = chrono::steady_clock::now();
    printf("Linear elapsed time: %d [microsec]\n",
           chrono::duration_cast<chrono::microseconds>(endTime - beginTime).count());
    //printResult(result);
}