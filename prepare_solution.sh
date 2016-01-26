#
# Script to checkout target repo and prepare for test.
#
rm -rf solutions
mkdir solutions
git clone "$TARGET_REPO" solutions
echo "After clone"
pwd
cd solutions
pwd
git checkout solution
echo "Change branch"
rm -rf signup-uitest
ls -la
pwd
cp -r ../signup-uitest signup-uitest
echo "include ':signup-uitest'" >> settings.gradle