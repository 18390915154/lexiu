drop table if exists t_about_us;

/*==============================================================*/
/* Table: t_about_us                                            */
/*==============================================================*/
create table t_about_us
(
   about_id             int not null auto_increment comment '表id，自增长',
   about_content        char(100) comment '关于我们描述',
   about_status         int comment '0.不使用、1.使用',
   about_backup         char(50) comment '备份字段',
   primary key (about_id)
);

alter table t_about_us comment '关于我们';
