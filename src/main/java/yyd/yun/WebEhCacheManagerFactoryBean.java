package yyd.yun;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.ObjectExistsException;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import net.sf.ehcache.config.DiskStoreConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.core.io.Resource;

/**
 * Created by Administrator on 2017/4/24 0024.
 */
public class WebEhCacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * 配置文件路径
     */
    private Resource configLocation;
    /**
     * 缓存写入磁盘路径
     */
    private Resource diskStoreLocation;
    /**
     * 缓存管理器用来获取缓存
     */
    private CacheManager cacheManager;
    /**
     * 缓存名称
     */
    private String cacheManagerName;


    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    /**
     * 设置ehcahce的diskStore path，例如：/WEB-INF/cache
     * 如设置了此项，则在ehcache配置文件中不要配置<diskStore path=""/>，否则本设置无效。
     * @param diskStoreLocation
     */
    public void setDiskStoreLocation(Resource diskStoreLocation) {
        this.diskStoreLocation = diskStoreLocation;
    }

    public void setCacheManagerName(String cacheManagerName) {
        this.cacheManagerName = cacheManagerName;
    }

    public void destroy() throws Exception {
        this.cacheManager.shutdown();
    }

    public CacheManager getObject() throws Exception {
        return this.cacheManager;
    }

    public Class<?> getObjectType() {
        return (this.cacheManager != null ? this.cacheManager.getClass()
                : CacheManager.class);
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * 主要方法，用来初始化缓存管理器
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        logger.info("Initializing EhCache CacheManager");
        Configuration config = (this.configLocation != null ?
                EhCacheManagerUtils.parseConfiguration(this.configLocation) : ConfigurationFactory.parseConfiguration());
        if (this.diskStoreLocation != null) {
            DiskStoreConfiguration dc = new DiskStoreConfiguration();
            dc.setPath(this.diskStoreLocation.getFile().getAbsolutePath());
            try {
                config.addDiskStore(dc);
            } catch (ObjectExistsException e) {
                logger.warn("if you want to config distStore in spring,"
                        + " please remove diskStore in config file!", e);
            }
        }
        if (this.cacheManagerName != null) {
            config.setName(this.cacheManagerName);
        }
        if (config != null) {
            this.cacheManager = new CacheManager(config);
        }
    }
}
