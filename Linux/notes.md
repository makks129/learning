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
