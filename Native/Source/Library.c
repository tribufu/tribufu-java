// Copyright (c) TribuFu. All Rights Reserved

#include "Library.h"
#include "TribuFu.h"

char *Java_Hello(char *input)
{
    char *result = Hello("C");

    return result;
}
