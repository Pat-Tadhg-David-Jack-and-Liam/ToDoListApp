# CS3500
Hey guys added this just to help with setting up the ssh keys with github 

if your on windows I recommend downloading the git bash terminal.
you can add it to vs code and it allows you to use bash commands.

check if you already have keys 
ls -al ~/.ssh

you'll know you have a key pair if the public key is named something like this
id_rsa.pub
id_ecdsa.pub
id_ed25519.pub

if you don't have a key pair generate them using
ssh-keygen -t ed25519 -C "your_githubemail@example.com"

you'll need to now start an ssh agent and add your keys to it
you'll probably learn these commands pretty quick cause you'll have to start a ssh agent when your system restarts
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519

now you have to add the public key to your github account
copy it
cat ~/.ssh/id_ed25519.pub
add it to the SSH setting on the github website

you should now be able to clone the repo using
git clone git@github.com:Pat-Tadhg-David-Jack-and-Liam/CS3500.git


# Some handy git commands :)

## See uncommited changes
git diff

## See commits
git log

## See your latest commit changes
git log -p

## Deletes most recent commit
git reset --soft HEAD~1

# GIT WORKFLOW
git merge origin/main

Do work

git merge origin/main

git push 

Then go to your branch page on the github website and open a pull request and then merge from that pull request

# Liam's workflow
Note that all commands are untested and written from memory, use them at your own risk and don't take them as gospel.

- First you pull changes, this makes sure that your local is up to date with the origin (both your branch(liam) and main should be up to date)
`git pull origin main`
`git pull origin liam`

- Then you will make changes to your branch (be sure you checked out to your branch before coding) and commit these changes
`git commit -m "my message"`
- Once finished you will need to pull from remote again to make sure your up to date
- You can then safely push to origin/liam
`git push origin/liam`
    - Note that if you don't have a branch on the remote called liam (tracking branch) then you will need to create clone
    `git push origin liam`
- You can now merge with origin/main
`git checkout origin/liam`
`git merge origin/master`

