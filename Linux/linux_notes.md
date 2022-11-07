## FS basics

Everything in Linux is a file (commands, configs, devices, etc.)

`/` - root
- `home` - dir for users' home dirs
- `root` - dir for root home dir
- `bin` - binaries (Linux commands are here also)
- `sbin` - super bin (commands only for admin)
- `usr` - user dir
  - `bin`/`sbin` - mostly overlap with root `bin`/`sbin`
  - `local` - also command binaries (but also for custom ones)
  - `lib` - libs that command binaries share
- `etc` - [ehtsee] configs, etc
  - `network`
    - `interfaces` - network interfaces/settings
- `boot` - files needed to boot
- `var` - log files, etc
- `tmp` - temporary files
- `lib` - shared lib files
- `dev` - devices
  - `vda`/`vda1`/`sda`/`sda1` - hard drives
- `media` - mount drives (by system)
- `mnt` - mount drives (manual mount)


### Permissions

`----------` - file
`d---------` - dir
`-rwx------` - owner
`----rwx---` - group
`-------rwx` - other (everyone)

#### chmod / chown

`chown <user>:<group> <file>` - change file ownership

`chmod <permissions> <file>`
- (u)ser, (g)roup, (o)ther, (a)ll
- (r)ead, (w)rite, (e)xecute
- (+) add permission, (-) remove permission, (=) set permission

**Numeric**: read(4) + write(2) + execute(1)

| Decimal | Binary | Permission | Permission meaning       |
| ------- | ------ | ---------- | ------------------------ |
| 7       | 111    | rwx        | read, write, and execute |
| 6       | 110    | rw-        | read and write           |
| 5       | 101    | r-x        | read and execute         |
| 4       | 100    | r--        | read only                |
| 3       | 011    | -wx        | write and execute        |
| 2       | 010    | -w-        | write only               |
| 1       | 001    | --x        | execute only             |
| 0       | 000    | ---        | none                     |

**Examples**
- `chmod o+rwx <file>` - add rwx for other
- `chmod o=rw <file>` - set rw for other
- `chmod og-x <file>` - remove x from other and group
- `chmod a+r <file>` - everyone can read
- `chmod 724 <file>`
  - 1st: owner = everything(7 = 4+2+1)
  - 2nd: group = writing(2)
  - 3rd: other = reading(4)


## Utils and Navigation

`whoami` - current user

`which` - file/dir location

`pwd` - current dir

`$PWD` - var for current dir

`$OLDPWD` - var for prev dir (`cd -` will use this var to switch to prev dir)

`tree` - show file tree from this dir

`sudo !!` - run prev command w sudo

CTRL+X+E - open current command in default text editor

CTRL+R - reverse search

`history` - command history

### Files

`touch`

`mkdir`
- `-p` - create dirs (e.g. `mkdir -p x/y/z` will create x, y, z structure)

`cat` / `less` / `tail`

`tail -f <file>` - tail file in a foreground

`cat > file.txt` (CTRL+D to save) - write to file

`cat << EOF > file.txt` (`EOF`+Enter to finish) - multiline write to file and finish on `EOF`

`echo "foo" > file.txt` - write to file

`mv <dir/file> <dir/file>` - move/rename

`cp <dir/file> <dir/file>` - copy
- `-r` - recursive, copy all files inside a dir too

`rm` - remove file
- `-rf` - recursive, force

`rmdir` - remove dir

## Users

`/etc/passwd`
list of all users
format: username, password, uid, gid, input data (name, phone, etc), home dir, default shell

`/etc/shadow`
passwords

`adduser <user>`
create user, create group, set pw, create home dir
need to have correct permissions in sudoers file to add users

`useradd <user>`
just create user
- `-m` also create a home dir

`userdel <user>`
delete user

`passwd <user>`
add password for user

`usermod`
modify user
- `--shell <shell>` change default shell
- `-l <newname> <oldname>` change name
- `-G <group> <user>` add to only that group
  - `-a` append group to groups

`groupadd <group>`
add group

`groups`
list all groups this user is in

`gpasswd`
- `-d <user> <group>` remove user from a group

`groupdel <group>`
delete group (doesn't delete users in it)

`su`
switch user
- `-` to root
- `- <user>` to user

`visudo`
edit sudoers file


## Packages

### dpkg
Low-level package manager
Installs package files (binaries, e.g. `.deb`)
`.deb` - Debian based packages for Linux
`.rpm` - base for packages of some other systems

### apt
Advanced Package Tool
Installs packages from apt repository

`apt update` - updates list of things that can be downloaded from apt repo. Do this before every install
`apt install <package>`
`apt remove <package>` - (safe) remove package but not user data
`apt purge <package>` - (unsafe) remove package and user data

`apt list` - list all available packages
`apt list --installed` - installed packages on your system
`apt show <package>` - info about a package

`apt upgrade` - update your packages (usually used with `apt update` before that)
`apt full-upgrade` - will also remove old versions


## Processes / Daemons

**Process** - instance of a running program
**Deamon** - non-interractive process (have `d` at the end of the name, like `sshd`), aka Unit

### Processes

Processes can be foreground (e.g. a shell program you need to quit with ctrl+c) or background

`ps -aux` - list all processes
`pstree` - show process tree

`top` - real-time list of processes by CPU usage
`htop` - similar but with more data

`pgrep <process>` - returns process id

`kill <pid>` (`pkill <process>`) - kill a process by id (or by name)
- `-#` - # is a number, specify kill code

Kill codes (`kill -l`):
- `-15`-`SIGTERM` - default signal sent on kill (soft-kill, more like request to kill)
- `-2`-`SIGINT`) - CTRL+C - interrupt
- `-19`-`SIGSTOP` / `18`-`SIGCONT` - CTRL+Z - will put foreground process to sleep (it will be stopped but will still be a foreground process), or then return it back from sleep
- `-9`-`SIGKILL` - hard-kill


`jobs` - lists foreground processes

`bg <job_id>` - puts a foreground process to the background

`fg <job_id>` - puts a background process to the foreground

`<some_process_start_command> &` - starts running a process in the background

### Daemons

`systemd` - master daemon:
- service manager
- init system

`systemctl`
- `start <unit>`
- `stop <unit>`
- `restart <unit>`
- `reload <unit>` - just reload config w/o restarting the unit
- `reload-or-restart <unit>` - reload if possible, otherwise restart
- `status <unit>`
- `disable <unit>` - prevent unit from starting up automatically on boot
- `enable <unit>`
- `list-units` - list active units (services, timers, devices, etc)
  - `--all` - list all (also inactive)
  - `-t <type>` - list units of type (e.g. `-t service`)
