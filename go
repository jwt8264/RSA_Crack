#/bin/bash
javac CrackRsa.java
echo "compiled"
java CrackRsa 42703 10097728250125472783
echo "= = = = = = = = ="
echo "expect:"
echo "2689840999"
echo "3754024217"
echo "4164133535611856527"
