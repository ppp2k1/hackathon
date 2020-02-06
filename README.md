# Security monitoring services exposed via GraphQL gateway


## Build

* `git clone https://github.com/ppp2k1/hackathon.git`

* `gradlew.bat clean`

* `gradlew.bat`

## To start the images

* `docker-compose up`

**Containers and ports**

* `service-registry 8761`

* `graphql-gateway 7777`

* `configuration-service 8888`

* `ontap-service 5555`

* `vmware-service 9999`

## Test

* `Configuration Serivce`

https://github.com/ppp2k1/hackathon-git-repo/blob/master/application.properties

http://localhost:8888/admin/env


* `Service Registry`
http://localhost:8761/

* `VMware Service`
GET http://localhost:9999/vmware/config?vmName=vm-01


* `ONTAP Serivce`
** GET http://localhost:5555/ontap/config?path=10.193.48.51:/Test_W1

** GET http://localhost:5555/ontap/cluster?name=10.193.48.51

** GET http://localhost:5555/ontap/vserver?name=azure-svm-1


* `GraphQL gateway`

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



