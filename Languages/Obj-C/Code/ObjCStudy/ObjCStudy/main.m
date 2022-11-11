//
//  main.m
//  ObjCStudy
//
//  Created by Makks on 25.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "XYZPerson.h"

void func(){
    NSLog(@"END");
}

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        // (1) Testing object creation and method invocation
//        XYZPerson *pers = [[XYZPerson alloc] init];
//        [pers sayHello];
//        [pers sayWtf];
//        [XYZPerson greetFromPerson:pers];
//        
//        XYZPerson *pers2 = [XYZPerson new];
//        XYZPerson *pers3 = [XYZPerson person];
//        XYZPerson *pers4;
//        if (pers3) {
//            NSLog(@"pers3 pointer points XYZPerson instance");
//        }
//        if (!pers4) {
//            NSLog(@"pers4 pointer points to nothing");
//        }
//
//        NSMutableString *mutableName = [NSMutableString stringWithString:@"John"];
//        XYZPerson *pers5 = [XYZPerson personWithFirstName:mutableName secondName:@"Doe" dateOfBirth:[NSDate date]];
//        [pers5 sayHello];
//        [mutableName appendString:@"ny"];
//        [pers5 sayHello];
//        
//        __weak XYZPerson *pers6 = [XYZPerson personWithFirstName:@"pers6" secondName:nil dateOfBirth:nil];
//        
//        XYZPerson *__weak pers7 = [XYZPerson personWithFirstName:@"pers7" secondName:nil dateOfBirth:nil];
//        
//        XYZPerson *pers8 = [XYZPerson personWithFirstName:@"pers8" secondName:nil dateOfBirth:nil];
//        
//        pers8 = nil;
        
        
        // (2) Testing strong/weak references and deallocation
//        XYZPerson *test = [XYZPerson person];
//        
//        XYZPerson *john = [XYZPerson personWithFirstName:@"John" secondName:@"Doe" dateOfBirth:nil];
//        XYZPerson *alice = [XYZPerson personWithFirstName:@"Alice" secondName:@"Doe" dateOfBirth:nil];
//        john.partner = alice;
//        NSLog(@"John's partner is %@", john.partner);
//        NSLog(@"Alice's partner is %@", alice.partner);
//        john = nil;
//        NSLog(@"John's partner is %@", john.partner);
//        NSLog(@"Alice's partner is %@", alice.partner);
//        
//        [test asyncSleep5];
        
        
        // (3) Testing categories and extensions
        XYZPerson *pers = [XYZPerson person];
        pers.testProperty = 2;
        pers.extensionOnlyProperty = 1;
        [pers extensionOnlyMethod];
        
        
        
        
        
        
        NSLog(@"END");
        
    }
    return 0;
}































