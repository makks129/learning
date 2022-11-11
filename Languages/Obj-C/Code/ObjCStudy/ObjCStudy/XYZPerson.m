//
//  XYZPerson.m
//  ObjCStudy
//
//  Created by Makks on 25.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import "XYZPerson.h"

@interface XYZPerson () {
    int _extensionIvar;
}

@property (readwrite) int testProperty;
@property int extensionOnlyProperty;

@property int height;
@property int weight;

- (void)extensionOnlyMethod;

@end



@implementation XYZPerson {
    int _implIvar;
}

- (id)init {
    return [self initWithFirstName:nil secondName:nil dateOfBirth:nil];
}

- (id)initWithFirstName:(NSString *)firstName secondName:(NSString *)secondName dateOfBirth:(NSDate *)dob {
    self = [super init];
    if (self) {
        _firstName = [firstName copy];
        _secondName = [secondName copy];
        _dateOfBirth = dob;
    }
    return self;
}

+ (id)person {
    return [[self alloc]init];
}

+ (id)personWithFirstName:(NSString *)firstName secondName:(NSString *)secondName dateOfBirth:(NSDate *)dob {
    return [[self alloc] initWithFirstName:firstName secondName:secondName dateOfBirth:dob];
}

+ (void)greetFromPerson:(XYZPerson *)person {
    [person sayHello];
}

- (void)saySomething:(NSString *)string {
    NSLog(@"%@", string);
    self.alive = NO;
}
- (void)sayHello {
    NSString *hello = [NSString stringWithFormat:@"Hello, %@ %@", self.firstName, self.secondName];
    [self saySomething:hello];
}
- (void)sayWtf {
    [self saySomething:[@"wtf" uppercaseString]];
}

- (void)setPartner:(XYZPerson *)partner {
    if (!_partner) {
        _partner = partner;
        partner.partner = self;
    }
}

- (void)testMethod {
    _testProperty = 1;
    self->_testProperty = 2;
}

- (void)extensionOnlyMethod {
    self.testProperty = 2;
    [self extensionOnlyMethod];
    NSLog(@"Calling extensionOnlyMethod");
}



- (void)mesureHeight {
    self.height = 178;
}

- (void)mesureWeight {
    self.weight = 68;
}

- (void)blockTestMethod {
    void (^(^complexBlock)(void(^)(void))) (void) = ^ (void(^argBlock)(void)){
        return ^{};
    };
}


- (void)testingProtocolMethod {
    if ([self.dataSource respondsToSelector:@selector(optionalMethod)]) {
        [self.dataSource optionalMethod];
    }
}


- (void)asyncSleep5 {
    [self performSelectorInBackground:@selector(saySomething) withObject:nil];
}

- (void)saySomething{
    sleep(5);
    NSLog(@"END async sleep5 thread");
}


- (void)dealloc {
    NSLog(@"%@ deallocated", self);
}

- (NSString *)description {
    return [NSString stringWithFormat:@"XYZPerson: %@", self.firstName];
}

@end
