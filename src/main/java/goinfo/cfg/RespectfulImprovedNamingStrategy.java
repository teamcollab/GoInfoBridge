package goinfo.cfg;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class RespectfulImprovedNamingStrategy extends ImprovedNamingStrategy
{
    @Override
    public String columnName(String columnName)
    {
        return columnName;
    }

    @Override
    public String tableName(String tableName)
    {
        return tableName;
    }
}