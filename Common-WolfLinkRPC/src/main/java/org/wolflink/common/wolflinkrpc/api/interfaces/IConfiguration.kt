package org.wolflink.common.wolflinkrpc.api.interfaces

import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder
import org.wolflink.common.wolflinkrpc.api.annotations.AnalyseFunction
import org.wolflink.common.wolflinkrpc.api.enums.ClientType
import org.wolflink.common.wolflinkrpc.api.interfaces.analyse.IAnalyse
import org.wolflink.common.wolflinkrpc.utils.ReflectionUtil

interface IConfiguration {
    fun getHost() : String
    fun getPort() : Int
    fun getUsername() : String
    fun getPassword() : String
    fun getQueueName() : String
    fun getLogger() : ILogger
    fun getClientType() : ClientType
    //默认通过反射获取
    fun getAnalyseFunctionList() : MutableList<IAnalyse>
    {
        val list = mutableListOf<IAnalyse>()
        var classes = ReflectionUtil.getClasses(getMainClass(),getAnalyseFunctionPackage())
        classes = ReflectionUtil.filterClassesByAnnotation(classes,AnalyseFunction::class.java)
        for (clazz in classes) list.add(clazz.getConstructor().newInstance() as IAnalyse)
        return list
    }
    fun getAnalyseFunctionPackage() : String
    fun getMainClass() : Class<*>
}