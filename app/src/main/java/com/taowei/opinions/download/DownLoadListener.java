package com.taowei.opinions.download;


import com.taowei.opinions.data.dbdata.SQLInfo;

/**
 * 类功能描述：</br>
 *
 * </p>
 */
public interface DownLoadListener {
    
    /**
     * (开始下载文件) 
     * @param sqlDownLoadInfo 下载任务对象
     */
    public void onStart(SQLInfo sqlDownLoadInfo);
    
    /**
     * (文件下载进度情况) 
     * @param sqlDownLoadInfo 下载任务对象
     * @param isSupportBreakpoint 服务器是否支持断点续传
     */
    public void onProgress(SQLInfo sqlDownLoadInfo, boolean isSupportBreakpoint);
    
    /**
     * (停止下载完毕) 
     * @param sqlDownLoadInfo 下载任务对象
      * @param isSupportBreakpoint 服务器是否支持断点续传
     */
    public void onStop(SQLInfo sqlDownLoadInfo, boolean isSupportBreakpoint);
    
    /**
     * (文件下载失败) 
     * @param sqlDownLoadInfo 下载任务对象
     */
    public void onError(SQLInfo sqlDownLoadInfo);
    
    
    /**
     * (文件下载成功) 
     * @param sqlDownLoadInfo 下载任务对象
     */
    public void onSuccess(SQLInfo sqlDownLoadInfo);
}
