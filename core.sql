/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/6 17:07:40                            */
/*==============================================================*/


drop table if exists sys_group;

drop table if exists sys_group_role;

drop table if exists sys_group_user;

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_user;

drop table if exists sys_user_role;

drop table if exists tb_role_menu;

/*==============================================================*/
/* Table: sys_group                                             */
/*==============================================================*/
create table sys_group
(
   id                   bigint(18) not null auto_increment,
   name                 VARCHAR(120) not null,
   status               CHAR(1) not null,
   is_removed           CHAR(1) not null,
   primary key (id)
);

alter table sys_group comment '用户组';

/*==============================================================*/
/* Table: sys_group_role                                        */
/*==============================================================*/
create table sys_group_role
(
   id                   bigint(18) not null,
   role_id              bigint(18) not null,
   group_id             bigint(18) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: sys_group_user                                        */
/*==============================================================*/
create table sys_group_user
(
   id                   bigint(18) not null auto_increment,
   group_id             bigint(18) not null,
   user_id              bigint(18) not null,
   primary key (id)
);

alter table sys_group_user comment '组与用户表';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   bigint(18) not null auto_increment comment '菜单ID',
   parent_id            bigint(18) default 0 comment '父级ID',
   menu_type            char(1) not null default '' comment '菜单类型，D表示栏目，M表示菜单,B表示按钮',
   name                 varchar(50) default '' comment '菜单名称',
   url                  varchar(200) default '#' comment '菜单地址',
   permission           varchar(100) default '' comment '权限标识',
   menu_order           int(5) default 99999 comment '菜单顺序',
   visble               char(1) default '1' comment '是否显示，1显示，0不显示',
   icon                 varchar(100) default '' comment '菜单图标',
   remark               varchar(500) default '' comment '备注',
   create_time          datetime comment '创建时间',
   create_by            bigint(18) comment '创建者',
   update_time          datetime comment '变更时间',
   update_by            bigint(18) comment '变更者',
   primary key (id)
);

alter table sys_menu comment '菜单表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   bigint(18) not null auto_increment,
   name                 VARCHAR(120) not null,
   code                 VARCHAR(120) not null,
   remark               VARCHAR(120),
   is_removed           CHAR(1) not null comment '1表示允许删除，0表示不允许删除',
   create_time          datetime,
   create_by            bigint(18),
   update_time          datetime,
   update_by            bigint(18),
   primary key (id)
);

alter table sys_role comment '角色表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   bigint(18) not null auto_increment,
   login_name           VARCHAR(120) not null,
   password             VARCHAR(120) not null,
   name                 VARCHAR(120) not null,
   phone                CHAR(11),
   status               CHAR(1) not null,
   email                VARCHAR(120),
   user_order           int(8) not null,
   internal             CHAR(1) not null comment '1表示内含账号，0表示不是',
   create_time          datetime,
   create_by            bigint(18),
   update_time          datetime,
   update_by            bigint(18),
   primary key (id),
   unique key AK_Key_2 (login_name)
);

alter table sys_user comment '用户表';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   bigint(18) not null,
   user_id              bigint(18) not null,
   role_id              bigint(18) not null,
   primary key (id)
);

alter table sys_user_role comment '用户与角色关联表';

/*==============================================================*/
/* Table: tb_role_menu                                          */
/*==============================================================*/
create table tb_role_menu
(
   id                   bigint(18) not null auto_increment comment '主键',
   role_id              bigint(18) not null comment '角色ID',
   menu_id              bigint(18) not null comment '菜单ID',
   primary key (id)
);

alter table tb_role_menu comment '角色菜单关联表';

alter table sys_group_role add constraint FK_GR_GROUP_ID foreign key (group_id)
      references sys_group (id) on delete restrict on update restrict;

alter table sys_group_role add constraint FK_GR_ROLE_ID foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

alter table sys_group_user add constraint FK_GU_GROUP_ID foreign key (group_id)
      references sys_group (id) on delete restrict on update restrict;

alter table sys_group_user add constraint FK_GU_USER_ID foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_UR_ROLE_ID foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_UR_USER_ID foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;

alter table tb_role_menu add constraint FK_rm_menu_id foreign key (menu_id)
      references sys_menu (id) on delete restrict on update restrict;

alter table tb_role_menu add constraint FK_rm_role_id foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

