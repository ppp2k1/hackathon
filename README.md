# Security monitoring services exposed via GraphQL gateway


**To build** 

`1. git clone https://github.com/ppp2k1/hackathon.git`

`2. gradlew.bat clean`

`3. gradlew.bat`

**To start the images**
`docker-compose up`

**Containers and ports**

`1. service-registry 8761`

`2. graphql-gateway 7777`

`3. configuration-service 8888`

`4. ontap-service 5555`

`5. vmware-service 9999`

**To Test**

`Configuration Serivce`

https://github.com/ppp2k1/hackathon-git-repo/blob/master/application.properties
http://localhost:8888/admin/env


`Service Registry`
http://localhost:8761/

`VMware Service`
GET http://localhost:9999/vmware/config?vmName=vm-01


`ONTAP Serivce`
http://localhost:5555/ontap/config?path=10.193.48.51:/Test_W1
http://localhost:5555/ontap/cluster?name=10.193.48.51
http://localhost:5555/ontap/vserver?name=azure-svm-1


`GraphQL gateway`
POST http://localhost:7777/graphql


{
    SecurityConfigByVm(name: "vm-001") {
        name
        vmware{
            id
            ipAddress
            vmName
            host{
                ipAdd
                firewallOn
                firewallLoaded
                firewallDefaultAction
            }
            datastore{
                type
                name
                url
                remoteHost
                remotePath
            }
            vmdk{
                vmdkName
                vmdkFilePath
                datastoreName
            }
        }
        ontap{
            ip
            volume{
                softwareEncrypt
                hardwareEncrypt
                volumeAntivirusAttributes{
                    onAccessPolicy
                }
                volumeExportAttributes{
                    policy
                }
                volumeSecurityAttributes{
                    style
                    volumeSecurityUnixAttributes{
                        groupId
                        permissions
                        userId
                    }
                }
                volumeIdAttributes{
                    uuid
                    name
                    node
                    junctionPath
                    type
                    style
                    styleExtended
                    containingAggregateName
                    containingAggregateUuid
                    owningVserverName
                    aggrList
                }
            }
            vserver{
                loginBannerConfigured
                sshCiphersSecured
                cifsLdapSigned
                CifsLdapSealed
                nfsKerberoseEnabled
                iscsiChapEnabled
            }
            cluster{
                telnetEnabled
                sshCiphersSecured
                asupHttpsConfigured
                fipsEnabled
                loginBannerConfigured
            }
        }
    }
}



