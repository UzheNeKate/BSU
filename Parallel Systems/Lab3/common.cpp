//
// Created by 1111 on 28.10.21.
//

#include <string>
#include <map>
#include <iostream>
#include <fstream>
#include "common.h"

using namespace std;

int findInFile(const string &desiredWord, const string &fileName) {
    ifstream fin(fileName);
    int counter = 0;
    while (!fin.eof()) {
        string buf;
        fin >> buf;
        if (buf == desiredWord) {
            counter++;
        }
    }
    fin.close();
    return counter;
}

void printResult(map<string, int> &result) {
    for (auto const &fileResult: result) {
        cout << fileResult.first << " : " << fileResult.second << '\n';
    }
}