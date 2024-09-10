secretId=arn:aws:secretsmanager:ap-northeast-2:675195215904:secret:secret/strawberryfields-3dW9D0
aws secretsmanager update-secret --secret-id ${secretId} --secret-string file://config.json