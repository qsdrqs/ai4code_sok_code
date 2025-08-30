public boolean isPass(String sql) {
        List<QueryTable> list = null;
        //【jeecg-boot/issues/4040】在线报表不支持子查询，解析报错 #4040
        try {
            list = this.getQueryTableInfo(sql.toLowerCase());
        } catch (Exception e) {
            log.warn("校验sql语句，解析报错：{}",e.getMessage());
        }
        
        if(list==null){
            return true;
        }
        log.info("--获取sql信息--", list.toString());
        boolean flag = true;
        for (QueryTable table : list) {
            String name = table.getName();
            String fieldString = ruleMap.get(name);
            // 有没有配置这张表
            if (fieldString != null) {
                if ("*".equals(fieldString) || table.isAll()) {
                    flag = false;
                    log.warn("sql黑名单校验，表【"+name+"】禁止查询");
                    break;
                } else if (table.existSameField(fieldString)) {
                    flag = false;
                    break;
                }

            }
        }
        return flag;
    }