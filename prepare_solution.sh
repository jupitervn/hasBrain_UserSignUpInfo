#
# Script to checkout target repo and prepare for test.
# This script will clone the solution code into "solutions" directory
# Then copy the ui test folder into solutions and include it into the settings.gradle
# It will also add the read/write external permission so that spoon screenshot can work correctly.
#
rm -rf solutions
mkdir solutions
git clone "$TARGET_REPO" solutions
echo "After clone"
function add_permissions {
  local PERMISSION_LINE="<uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\"\/><uses-permission android:name=\"android.permission.READ_EXTERNAL_STORAGE\"\/><\/manifest>"
  echo "${PERMISSION_LINE}"
  find . -not -path "*build/*" -name "AndroidManifest.xml" -exec echo "FILE NAME {}" \;
  find . -not -path "*build/*" -name "AndroidManifest.xml" -exec sed -i.bak "s/<\/manifest>/${PERMISSION_LINE}/" "{}" \;
}
cd solutions
git checkout solution
add_permissions
find . -not -path "*build/*" -name "AndroidManifest.xml" -exec cat {} \;
rm -rf signup-uitest
cp -r ../signup-uitest signup-uitest
echo "include ':signup-uitest'" >> settings.gradle