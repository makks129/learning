//
//  main.c
//  c_test
//
//  Created by Makks on 20.02.14.
//
//

#include <stdio.h>

typedef struct Point {
    double x;
    double y;
} CGPoint;


int main(int argc, char *argv[]) {
    
    char en = 's';
    char *fr = "é";
    
    printf("%lu\n", sizeof(en));
    printf("%lu\n", sizeof(*fr));
    
    CGPoint p;
    p.x = 2;
    p.y = 3;
    
    CGPoint *pp = &p;
    pp->x = 2;
    pp->y = 3;
    
    
    
    return 0;
}