//
//  XYZPerson.h
//  ObjCStudy
//
//  Created by Makks on 25.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TestProtocol.h"

@interface XYZPerson : NSObject

@property (copy) NSString *firstName;
@property (copy) NSString *secondName;
@property NSDate *dateOfBirth;
@property (getter=isAlive, setter=setAlive:) BOOL alive;
@property (weak, nonatomic) XYZPerson *partner;
@property (readonly) int testProperty;

@property (readonly) int height;
@property (readonly) NSUInteger weight;

@property (weak) id <TestProtocol> dataSource;

- (void)measureHeight;
- (void)measureWeight;

+ (id)person;
+ (id)personWithFirstName:(NSString *)fn secondName:(NSString *)sn dateOfBirth:(NSDate *)dob;
+ (void)greetFromPerson:(XYZPerson *)person;
- (void)saySomething:(NSString *)string;
- (void)sayHello;
- (void)sayWtf;

- (void)asyncSleep5;

@end