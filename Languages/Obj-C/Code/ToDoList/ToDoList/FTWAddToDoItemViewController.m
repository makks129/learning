//
//  FTWAddToDoItemViewController.m
//  ToDoList
//
//  Created by Makks on 03.03.14.
//  Copyright (c) 2014 Makks. All rights reserved.
//

#import "FTWAddToDoItemViewController.h"

@interface FTWAddToDoItemViewController ()

@property (weak, nonatomic) IBOutlet UITextField *textField;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *btnDone;

- (IBAction)doneButtonClicked:(id)sender;

@end

@implementation FTWAddToDoItemViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)doneButtonClicked:(id)sender {
//    if (self.textField.text.length > 0) {
//        self.toDoItem = [[FTWToDoItem alloc] init];
//        self.toDoItem.completed = NO;
//        self.toDoItem.name = self.textField.text;
//    }
//    [self performSegueWithIdentifier:@"UnwindToToDoList" sender:self];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if (sender == self.btnDone) {
        if (self.textField.text.length > 0) {
            self.toDoItem = [[FTWToDoItem alloc] init];
            self.toDoItem.completed = NO;
            self.toDoItem.name = self.textField.text;
        }
    }
}

@end
