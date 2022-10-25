## Commands

`add`
- `--patch`     staging files while revising every change

`push`
- `-u origin <branch_name>`    new remote branch with this name will be created. -u - will set this branch as an upstream branch, so that all future pushes are done against this remote branch automatically
- `origin --delete <remote-branch-name>`     delete remote branch

`pull`
- `--rebase`      read here: https://stackoverflow.com/a/48929413/1765558

`rm`
- `--cached <file>`     untrack an uncommited file

`reset`
- `HEAD <file>`     unstage a staged file

`bisect`      find broken commit (see: https://www.metaltoad.com/blog/beginners-guide-git-bisect-process-elimination)

--------------------------------------------------------------------------------

## Processes

TODO: Reverting

### Skipping git checks
[Skipping git checks](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/collaborating-on-repositories-with-code-quality-features/about-status-checks#skipping-and-requesting-checks-for-individual-commits)
```
git commit -m "Update README
>
>
skip-checks: true"
```

--------------------------------------------------------------------------------

## Concepts

Tracking / Staging

untracked: The file is new, Git knows nothing about it. If you git add <file>, it becomes:

staged: Now Git knows the file (tracked), but also made it part of the next commit batch (called the index). If you `git commit`, it becomes:

unchanged: The file has not changed since its last commit. If you modify it, it becomes:

unstaged: Modified but not part of the next commit yet. You can stage it again with `git add` will track untracked files, and stage any file.

Also: You can untrack an uncommited file with `git rm --cached <file>` and unstage a staged file with `git reset HEAD <file>`.
