//
//  FTWToDoListViewController.h
//  ToDoList
//
//  Created by Makks on 03.03.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FTWToDoListViewController : UITableViewController

@property NSMutableArray *toDoItems;

- (IBAction)unwindToList:(UIStoryboardSegue *)segue;

@end
