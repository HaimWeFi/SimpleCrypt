# SimpleCrypt
Simple RSA crypto utility

This utility can 
1. Generate private and public RSA keys in Base64 format
2. Decrypt a Base64 format encrypted text using a private key
3. Encrypt any text to a base64 format encrypted text using a public key

Usage:

java -jar SimpleCrypt.jar -genkeys -bits=[bits size]

java -jar SimpleCrypt.jar -decrypt -privk=[private key] -txt=[text to decrypt]

java -jar SimpleCrypt.jar -encrypt -pubk=[public key] -txt=[text to encrypt]

