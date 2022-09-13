//#include "Windows.h"
//#include <stdio.h>
//
//int n = 1000000;
//int main() {
//	double total = 0;
//	LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
//	double sum = 0., h = 1. / n, x;
//
//	QueryPerformanceFrequency(&liFrequency);
//	QueryPerformanceCounter(&liStartTime);
//
//	for (int i = 0; i < n; ++i) {
//		x = h * i;
//		sum += 4. / (1. + x * x);
//	}
//
//	QueryPerformanceCounter(&liFinishTime);
//	double dElapsedTime = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;
//		
//	printf("Time = %f\n", dElapsedTime);
//	printf("PI = %.16f\n", sum / n);
//}