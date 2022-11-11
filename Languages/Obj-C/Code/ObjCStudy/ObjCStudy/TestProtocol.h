//
//  TestProtocol.h
//  ObjCStudy
//
//  Created by Makks on 27.02.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol TestProtocol <NSObject>

- (int)requiredMethod;
@optional
- (int)optionalMethod;
@required
- (NSString *)requiredMethodWithParam:(NSString *)s;

@end
