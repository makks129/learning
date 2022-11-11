//
//  main.c
//  c_test
//
//  Created by Makks on 20.02.14.
//
//

#include <stdio.h>

/* echo command-line arguments; 2nd version */
main(int argc, char *argv[])
{
    while (--argc > 0)
        printf((argc > 1) ? "%s " : "%s", *++argv);
    printf("\n");
    return 0;
}