package com.wenzon.ppm.PM;

import android.content.Context;

/**
 * Created by Wenzon on 2018/1/20.
 */

public class ProjectDAOFactory {
    public static ProjectDAO getDAOInstance( Context context, PType dbType)
    {
        switch (dbType)
        {
            case MEMORY:
                return new ProjectMemoryDAO();
            case FILE:
                return new ProjectFileDAO(context);
        }
        return null;
    }

}
