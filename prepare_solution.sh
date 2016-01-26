#
# Script to checkout target repo and prepare for test.
#
rm -rf solutions
mkdir solutions
git clone "$TARGET_REPO" solutions
echo "After clone"
cd solutions
git checkout solution
rm -rf signup-uitest
cp -r ../signup-uitest signup-uitest
echo "include ':signup-uitest'" >> settings.gradle