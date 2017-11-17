/*
 Navicat Premium Data Transfer

 Source Server         : 10.10.30.181-开发
 Source Server Type    : PostgreSQL
 Source Server Version : 90603
 Source Host           : 10.10.30.181:5432
 Source Catalog        : haihang_cms
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90603
 File Encoding         : 65001

 Date: 15/11/2017 15:15:51
*/


-- ----------------------------
-- Table structure for card_e_t
-- ----------------------------
DROP TABLE IF EXISTS "public"."card_e_t";
CREATE TABLE "public"."card_e_t" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "iccid" varchar(32) COLLATE "pg_catalog"."default",
  "imsi" varchar(32) COLLATE "pg_catalog"."default",
  "msisdn" varchar(32) COLLATE "pg_catalog"."default",
  "pin1" varchar(128) COLLATE "pg_catalog"."default",
  "pin2" varchar(128) COLLATE "pg_catalog"."default",
  "puk1" varchar(128) COLLATE "pg_catalog"."default",
  "puk2" varchar(128) COLLATE "pg_catalog"."default",
  "customer_id" varchar(64) COLLATE "pg_catalog"."default",
  "bind_imei" varchar(32) COLLATE "pg_catalog"."default",
  "current_imei" varchar(32) COLLATE "pg_catalog"."default",
  "device_state_id" varchar(64) COLLATE "pg_catalog"."default",
  "sail_state_dic" varchar(200) COLLATE "pg_catalog"."default",
  "operator_key_dic" varchar(200) COLLATE "pg_catalog"."default",
  "channel_id" varchar(64) COLLATE "pg_catalog"."default",
  "material_dic" varchar(50) COLLATE "pg_catalog"."default",
  "type_dic" varchar(50) COLLATE "pg_catalog"."default",
  "in_net_date" timestamptz(6),
  "active_date" timestamptz(6),
  "refresh_date" timestamptz(6),
  "country_id" varchar(64) COLLATE "pg_catalog"."default",
  "province_id" varchar(64) COLLATE "pg_catalog"."default",
  "city_id" varchar(64) COLLATE "pg_catalog"."default",
  "create_by" varchar(64) COLLATE "pg_catalog"."default",
  "update_date" timestamptz(6),
  "create_date" timestamptz(6),
  "update_by" varchar(64) COLLATE "pg_catalog"."default",
  "del_by" varchar(64) COLLATE "pg_catalog"."default",
  "del_date" timestamptz(6),
  "del_flag" char(1) COLLATE "pg_catalog"."default",
  "perm_ext" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."card_e_t"."id" IS '主键';
COMMENT ON COLUMN "public"."card_e_t"."iccid" IS 'sim卡硬件标识';
COMMENT ON COLUMN "public"."card_e_t"."imsi" IS 'sim卡软件唯一标识';
COMMENT ON COLUMN "public"."card_e_t"."customer_id" IS '客户机构主键';
COMMENT ON COLUMN "public"."card_e_t"."device_state_id" IS '联网状态 0联网 1未联网';
COMMENT ON COLUMN "public"."card_e_t"."operator_key_dic" IS '所属运营商';
COMMENT ON COLUMN "public"."card_e_t"."channel_id" IS '所属渠道主键';
COMMENT ON COLUMN "public"."card_e_t"."material_dic" IS '材质 0：塑料 1：陶瓷';
COMMENT ON COLUMN "public"."card_e_t"."type_dic" IS 'sim卡尺寸类型  0：标准卡 1：Micro卡 2：Nano卡 3：5*6MM贴片卡';
COMMENT ON COLUMN "public"."card_e_t"."in_net_date" IS '入网时间';
COMMENT ON COLUMN "public"."card_e_t"."active_date" IS '开打时间';
COMMENT ON COLUMN "public"."card_e_t"."refresh_date" IS '数据刷新时间';
COMMENT ON COLUMN "public"."card_e_t"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."card_e_t"."update_date" IS '修改时间';
COMMENT ON COLUMN "public"."card_e_t"."create_date" IS '创建时间';
COMMENT ON COLUMN "public"."card_e_t"."update_by" IS '修改人';
COMMENT ON COLUMN "public"."card_e_t"."del_by" IS '删除人';
COMMENT ON COLUMN "public"."card_e_t"."del_date" IS '删除时间';
COMMENT ON COLUMN "public"."card_e_t"."del_flag" IS '删除状态 0 未删除 1已删除';
COMMENT ON TABLE "public"."card_e_t" IS 'SIM卡实体';

-- ----------------------------
-- Records of card_e_t
-- ----------------------------
INSERT INTO "public"."card_e_t" VALUES ('1', '123456', '456789', '123789', NULL, NULL, NULL, NULL, '778d9deed322462ea28dcf93c7a95051', NULL, NULL, NULL, 'proxy', 'CHINA_MOBILE', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL);

-- ----------------------------
-- Primary Key structure for table card_e_t
-- ----------------------------
ALTER TABLE "public"."card_e_t" ADD CONSTRAINT "pk_card_e_t" PRIMARY KEY ("id");
