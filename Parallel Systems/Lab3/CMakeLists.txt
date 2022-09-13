cmake_minimum_required(VERSION 3.20)
project(Lab3)

set(CMAKE_CXX_STANDARD 14)
# Add -O0 to remove optimizations when using gcc
IF(CMAKE_COMPILER_IS_GNUCC)
    set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -O0")
    set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -O0")
ENDIF(CMAKE_COMPILER_IS_GNUCC)

add_executable(Lab3 main.cpp seq_program.cpp seq_program.h parallel_p2p_program.cpp parallel_p2p_program.h producer_consumer.cpp producer_consumer.h common.cpp common.h)
