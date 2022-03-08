---
header-id: installing-blade-cli-with-proxy-requirements
---

# Installing Blade CLI with Proxy Requirements

[TOC levels=1-4]

If you have proxy server requirements and want to use Blade CLI, you must
configure your http(s) proxy for it using JPM: 

1.  Install JPM and Blade CLI using the Liferay Project SDK installer. Read the
    [Installing Blade CLI](/docs/7-2/reference/-/knowledge_base/r/installing-blade-cli)
    tutorial for more details.

2.  Execute the following command to configure your proxy requirements for Blade
    CLI:

    ```bash
    jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm
    ```

Excellent! You've configured Blade CLI with your proxy settings using JPM.
