-- Create table
create table JEEBIZ_DEMO (
  ID      	VARCHAR2(32) default sys_guid() not null,
  NAME    	VARCHAR2(100),
  TEXT    	CLOB null
);
-- Add comments to the table 
comment on table JEEBIZ_DEMO  is 'Demo示例表';
-- Add comments to the columns 
comment on column JEEBIZ_DEMO.ID  is 'ID';
comment on column JEEBIZ_DEMO.NAME  is '名称';
comment on column JEEBIZ_DEMO.TEXT  is '简述';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_SIMPLE_SIMPLE add constraint PK_JEEBIZ_DEMO primary key (ID);
