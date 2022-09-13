#include <iostream>
#include <mutex>
#include <thread>
#include <vector>
#include <sstream>
using namespace std;

class synchronized_cout {
private:
    mutex mtx_;
    ostringstream buffer_;

	void destruct()
	{
        lock_guard<mutex> guard(mtx_);
        cout << this->buffer_.str();
	}
public:
    template<class T>
    synchronized_cout& operator<<(const T& obj) {
        this->buffer_ << obj;
        return *this;
    }

    ~synchronized_cout() {
        destruct();
    }
};

void print(const int i)
{
    synchronized_cout() << "This " << "string is " << "from " << "thread " << i << "\n";
}

int main() {
    vector<thread> threads(15);
    for (int i = 0; i < 15; ++i) {
        threads[i] = thread(print, i);
    }
    for (int i = 0; i < 15; ++i) {
        threads[i].join();
    }
}
