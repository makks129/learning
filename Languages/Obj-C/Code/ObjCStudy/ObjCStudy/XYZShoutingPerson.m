//
//  XYZShoutingPerson.m
//  ObjCStudy
//
//  Created by Makks on 25.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import "XYZShoutingPerson.h"
#import "XYZPerson.m"

@implementation XYZShoutingPerson

- (void)saySomething:(NSString *)string {
    NSString *upperCaseString = [string uppercaseString];
    [super saySomething:upperCaseString];
}

- (void)subclassTestMethod {
    _extensionIvar = 2;
    _implIvar = 2;
    self.extensionOnlyProperty = 2;
    [self extensionOnlyMethod];
    
}

@end
