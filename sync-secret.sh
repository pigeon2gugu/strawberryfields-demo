secretId=arn:aws:secretsmanager:ap-northeast-2:675195215904:secret:secret/strawberryfields-3dW9D0
aws secretsmanager get-secret-value --secret-id ${secretId} --output json --query SecretString | jq -j > config.json