# Week 7 - Certificates under the hood

This week there will be class both Tuesday and Thursday.

The main coding we will be doing is the build a simple https server.

### Readings:
* [This page from Verisign](https://www.verisign.com/en_US/website-presence/website-optimization/ssl-certificates/index.xhtml) gives a good general intro (non-tech) to what certificates are.

* Many of the algorithms we have seen in the Java libraries use the Factory design pattern. [The wikipedia entry "Abstract factory" is the least wrong I found](https://en.wikipedia.org/wiki/Abstract_factory_pattern)

* What is a certificate in the first place? Wikipedia has two articles we will study in class
	* What is a [Public key certificate](https://en.wikipedia.org/wiki/Public_key_certificate)
	* What is the [X.509 standard](https://en.wikipedia.org/wiki/X.509)

The plan for the two days is that we Tuesday get a simple https server up and running using a selfsigned certicicate. The code for this is in this project.

And the exercise for Thursday is to get the same server running with a real certificate.

## Tuesdays plan
ssh tunnel: 
	`ssh -D 9999 -C <userinfo>` 
	
### Certificates in HTTPS
Overall activity diagrams ([stolen from this source](https://www.researchgate.net/publication/261464984_Socio-technical_formal_analysis_of_TLS_certificate_validation_in_modern_browsers))

1. [Activity diagram for certificate validation in Internet Explorer](https://www.researchgate.net/profile/Rosario_Giustolisi/publication/261464984/figure/fig2/AS:296824180101120@1447779807297/Activity-diagram-for-certificate-validation-in-Internet-Explorer.png)
2. [Activity diagram for certificate validation in Chrome](https://www.researchgate.net/profile/Rosario_Giustolisi/publication/261464984/figure/fig1/AS:296824175906819@1447779807145/Activity-diagram-for-certificate-validation-in-Chrome.png)
3. [Activity diagram for certificate validation in Firefox](https://www.researchgate.net/profile/Rosario_Giustolisi/publication/261464984/figure/fig3/AS:296824180101121@1447779807405/Activity-diagram-for-certificate-validation-in-Firefox.png)

### A SimpleHTTPS Server
1. Clone this project
2. Generate a self-signed certificate as described on https://stackoverflow.com/questions/2308479/simple-java-https-server (krishnakumar sekar's answer)
3. You can start it from netbeans, and connect to it from a browser (try several different browsers)

### Hierarchy of certificates
There are a [hierarchy of certificates](https://upload.wikimedia.org/wikipedia/commons/d/d1/Chain_of_trust.svg) ([taken from wikipedia](https://en.wikipedia.org/wiki/Public_key_certificate)). In addition to those in the figure, there is the one we have made which is a **self signed certificate**.

#### Exercises continued

4. Build the project, copy the jar file and the keystore file to a droplet and run the jar file in in a droplet
5. Connect to it using at least two different browers
6. Does your browser trust it next time around?

## Preperation for thursday
* Get a named droplet (or other linux machine), that is, a droplet you can connect to by a DNS name instead of an IP address. This is a two phase operation
	1.  Get a domain name. I got one from "GoDaddy", but there are many *registrars* available. I was able to set it up using [the "godaddy" secion in this blog](https://www.digitalocean.com/community/tutorials/how-to-point-to-digitalocean-nameservers-from-common-domain-registrars)
	2. Next you need to set it up on your droplet. Here I was able to get it done using [this blog from digital ocean](https://www.digitalocean.com/community/tutorials/how-to-set-up-a-host-name-with-digitalocean).

## Thursday plan
* Get the SimpleHTTPSServer to run without complaints with a "real certificate" (end-entity certificate)
* Recap the security of a certificate by going over the X.509 format and understanding which signatures, keys and hashes which are involved.
