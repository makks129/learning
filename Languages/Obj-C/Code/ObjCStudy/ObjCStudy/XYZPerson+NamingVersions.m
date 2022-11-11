//
//  XYZPerson+NamingVersions.m
//  ObjCStudy
//
//  Created by Makks on 27.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import "XYZPerson+NamingVersions.h"

@implementation XYZPerson (NamingVersions)

- (NSString *)fullNameSecondNameFirst {
    return [NSString stringWithFormat:@"%@ %@", self.secondName, self.firstName];
}


@end
