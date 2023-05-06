# vi

https://www.atmos.albany.edu/daes/atmclasses/atm350/vi_cheat_sheet.pdf

## Modes

`ESC` - command mode (CM)
`i` - insert mode (IM)


## Commands

### Basic

`:w` - save
`:w newfile.txt` - save as new file
`:q` - quit
`:wq` - save & quit
`:q!` - force quit

### Type

`u` - undo
`r` - redo

`dd` - delete a line
`#dd` - delete # lines (e.g. `3dd` deletes 3 lines)
`D` - delete everyone on the right of the cursor

`yy` - copy line
`#yy` - copy # lines
`p` - paste after current line

### Movement

`0` move to start of line
`$` move to end of line
`w` move to next word
`b` move to prev word
`:0` or `1G` move to first line
`:n` or `#G` move to # line
`:$` or `G` move to last line

### Search

`/<search>` - search
`?<search>` - search in backwards direction
`ENTER` to jump to first result
`n` - next
`N` - previous

### Replace

`:%s/<search>/<replaceWith>`
- add `/g` to replace all
- add `c` for confirmation


### Other

`:set nu` - show line numbers
