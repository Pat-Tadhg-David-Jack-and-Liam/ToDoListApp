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

// David is a good man ;)
// don't touch gradle
