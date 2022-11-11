//
//  FTWToDoItem.h
//  ToDoList
//
//  Created by Makks on 03.03.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FTWToDoItem : NSObject

@property NSString *name;
@property BOOL completed;
@property (readonly) NSDate *creationDate;

@end
