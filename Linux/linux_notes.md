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

Process - instance of a running program

`ps -aux` - list all processes
`pstree` - show process tree

Deamon - non-interractive process (have `d` at the end of the name, like `sshd`), aka Unit

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
