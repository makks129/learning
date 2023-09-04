## Commands

`checkout`
- `-`     Checkout prev branch

`add`
- `--patch`     Staging files while revising every change

`commit`
- `-am`     add . + message
- `--amend -m "<msg>"`    Update latest commit message. If additional files are added will also add them to prev commit. `--no-edit` instead of `-m` to not change commit message

`push`
- `-u origin <branch_name>`    New remote branch with this name will be created. -u - will set this branch as an upstream branch, so that all future pushes are done against this remote branch automatically
- `origin --delete <remote-branch-name>`     Delete remote branch
- `<remote> <branch>` 	Push changes from local branch to remote branch (doesn't care where you are checked out)
- `<remote> <source-branch>:<destination-branch>`	Same as above but allows specifying different source and destination branches

`pull`		Essentially the same as fetch+merge 
- `--rebase`      Read here: https://stackoverflow.com/a/48929413/1765558

`rm`
- `--cached <file>`     Untrack an uncommited file

`reset`
- `HEAD <file>`     Unstage a staged file

`bisect`      Find broken commit (see: https://www.metaltoad.com/blog/beginners-guide-git-bisect-process-elimination)

`config`
- `--global alias.ac "<command>"`     Add command alias

`log`
- `--graph --oneline --decorate`      Tree view of commit log

`fetch`		downloads remote changes to local representation of remote repo

--------------------------------------------------------------------------------
## Concepts

### Tracking / Staging

untracked: The file is new, Git knows nothing about it. If you git add <file>, it becomes:

staged: Now Git knows the file (tracked), but also made it part of the next commit batch (called the index). If you `git commit`, it becomes:

unchanged: The file has not changed since its last commit. If you modify it, it becomes:

unstaged: Modified but not part of the next commit yet. You can stage it again with `git add` will track untracked files, and stage any file.

Also: You can untrack an uncommited file with `git rm --cached <file>` and unstage a staged file with `git reset HEAD <file>`.

### HEAD

HEAD refers to the currently checked-out branch’s latest commit
Usually HEAD points to a branch (which points to a commit)
Detached HEAD means it points directly to a commit instead
To detach HEAD you can checkout the commit directly:
```sh
git checkout <commit-hash>
```

### Relative refs

__^__
`^` signifies a parent of the commit (one commit up)
```sh
git checkout master^ # checkout previous commit on master
```
`HEAD^` will also simply checkout previous commit before the one HEAD points to

Some commits might have multiple parents (like merge commits), in this case adding `^#` will move to #-th parent
```sh
# on merge commit
git checkout HEAD^2 # Will move to second parent of this merge commit
```

__~__
`~#` signifies # commits up the parent tree
```sh
git checkout HEAD~4 # checkout 4th commit up the tree
```

`^` and `~` can be chained
```sh
git checkout HEAD~^2~2 # Will move 1 up, then to 2nd parent, then 2 up
```

### Tags

Tags are permanent anchors to mark specific milestone commits
They don't move around like branches

`git describe` will show the steps to the latest tag in the format `<tag-name>_<steps>_g<current-commit-hash>`


### Remote

Remote branches reflect the state of remote repo, but they are local
`git fetch` updates local representation of remote repo

You can't work on these branches directly
When checked out you will be put into detached HEAD mode

Naming: `<remote-name>/<branch-name` (remote-name is mostly `origin`)

`git pull` does the same as `git fetch` + `git merge`, downloading the changes and updating current local branch


--------------------------------------------------------------------------------
## Main actions


### Branching

#### Creating and checking out
```sh
git branch new-branch # Create new branch
git checkout new-branch # Go onto a new branch
git checkout -b new-branch # Short version for both

git branch new-branch HEAD~2 # Create new branch from commit 2 up
```
#### Moving branches
```sh
git checkout new-branch
git branch -f master HEAD~3 # Will move master pointer to a commit 3 up the tree
```

### Combining

#### Merging
```sh
git checkout master
git merge new-branch 
# Will create a merge commit on master branch that has 2 parent: previous master commit and previous new-branch commit
# new-branch will still point to its last commit, not to the merge commit
# If we do the merge on new-branch now, both branches will point to merge commit
git checkout new-branch
git merge master
```
#### Rebasing

```sh
git checkout new-branch
git rebase master
# Will copy commits from new-branch and attach them to the latest commit on master
# master still points to the same commit as before, in order to move to to the same commit as new-branch we can rebase new-branch from master
git checkout master
git rebase new-branch
```
```sh
git rebase main new-branch # Will copy new-branch commits onto main
```
__Interractive__
```sh
git checkout master
git rebase -i HEAD~4
# Will copy the latest 3 commits on master and rebase them on the 4th commit up the tree
# master will now point to the latest of the copied commits
# -i is interactive mode which will allow to select/omit specific commits
```
#### Cherry-picking
```sh
git checkout master
git cherry-pick <commit1> <commit2> <commit3>
# This will create copies of commit1, commit2 and commit3 (wherever they are) on the master branch
```

### Reversing changes

#### Reset
`reset` moves branch pointer back to one of the previous commits
Should be used for local branches
```sh
git reset HEAD~1 # moves branch pointer to the previous commit
```
#### Revert
`revert` creates a new commit with reverted changes
Should be used for remote branches
```sh
git revert HEAD # create a new commit with reverted changes of current commit
```


--------------------------------------------------------------------------------
## Processes

### Changing local non-latest commit
```sh
git commit --amend
```

### Skipping git checks
[Skipping git checks](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/collaborating-on-repositories-with-code-quality-features/about-status-checks#skipping-and-requesting-checks-for-individual-commits)
```sh
git commit -m "Update README
>
>
skip-checks: true"
```

### Checking out separate commit
```sh
git fetch origin <full-commit-hash>:refs/remotes/origin/<new-branch>
get checkout <new-branch>
```

### Deleting remote branch
```sh
git push origin :branch # Having empty <source> (before `:`) pushes "nothing" to the remote, thus deleting a branch
```