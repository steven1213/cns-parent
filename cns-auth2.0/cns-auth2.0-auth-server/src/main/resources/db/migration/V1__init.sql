CREATE TABLE `oauth_access_token`
(
    `token_id`          varchar(255) DEFAULT NULL,
    `token`             blob,
    `authentication_id` varchar(255) DEFAULT NULL,
    `user_name`         varchar(255) DEFAULT NULL,
    `client_id`         varchar(255) DEFAULT NULL,
    `authentication`    blob,
    `refresh_token`     varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255)  DEFAULT NULL,
    `resource_ids`            varchar(255)  DEFAULT NULL,
    `client_secret`           varchar(255)  DEFAULT NULL,
    `scope`                   varchar(255)  DEFAULT NULL,
    `authorized_grant_types`  varchar(255)  DEFAULT NULL,
    `web_server_redirect_uri` varchar(255)  DEFAULT NULL,
    `authorities`             varchar(255)  DEFAULT NULL,
    `access_token_validity`   int(11) DEFAULT NULL,
    `refresh_token_validity`  int(11) DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             varchar(255)  DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(255) DEFAULT NULL,
    `token`          blob,
    `authentication` blob
