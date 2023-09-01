# Bitespeed Backend Task: Identity Reconciliation 

Bitespeed needs a way to identify and keep track of a customer's identity across multiple purchases. This module resolve the issue of Identity Reconciliation. 

## Tech Stack

**Backend:** Spring Boot, Mysql DB 


## API Reference

#### Get all items

```http
  POST /api/v1/identity/identity
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | Your Email|
| `phoneNumber` | `integer` | Your Phone Number|

Takes either email or phoneNumber or both and send response in json


## Screenshots

![App Screenshot](https://github.com/AashishMehtoliya/bitespeed-assignment/assets/36276413/c49027cc-3feb-4837-8eb6-719543e98eaf)

![App Screenshot](https://github.com/AashishMehtoliya/bitespeed-assignment/assets/36276413/aff3fd27-c0ce-4b8a-bacc-a155c84a251c)

![App Screenshot](https://github.com/AashishMehtoliya/bitespeed-assignment/assets/36276413/36853d79-b202-4cd4-8b89-b7925f31a9b6)

![App Screenshot](https://github.com/AashishMehtoliya/bitespeed-assignment/assets/36276413/ddefc820-cda4-4831-b50f-89dd5dd9fec2)

![App Screenshot](https://github.com/AashishMehtoliya/bitespeed-assignment/assets/36276413/4f045480-52ab-4841-b2a5-d9219342ddd5)








