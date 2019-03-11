#Viber EI Connector

The Viber [connector](https://docs.wso2.com/display/EI640/Working+with+Connectors) is a free to download app that allows users to make free calls and send messages to other Viber users .It works on both mobile and computer and can
be used to connect with people around the world.
It allows you to access the [Viber REST API](https://developers.viber.com/docs/api/rest-bot-api/) through WSO2 Enterprise Integrator (WSO2 EI).


## Compatibility

| Connector Version | Viber API Version | Supported WSO2 EI Version |
| ------------- | ------------- | ------------- |
| [1.0.0](https://github.com/wso2-extensions/esb-connector-viber/releases/tag/org.wso2.carbon.connector.viber-1.0.0)  | V7.3.0| ESB 6.4.0 |

## Getting started

###### Download and install the connector

1. Download the connector from [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/7181a316-bcac-4cbe-a617-a795abe4dcf3) by clicking the Download Connector button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with viber connector and their operations, see [Configuring Viber Connector  Operations](docs/config.md).

## Building From the Source

If you want to build viber connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-viber).
2. Run the following Maven command from the `esb-connector-viber` directory: `mvn clean install`.
3. The viber connector zip file is created in the `esb-connector-viber/target` directory.

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-viber/issues) for open issues that interest you. We look forward to receiving your contributions.

