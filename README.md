# UDPSocket
For this project I was curious to learn more about UDP sockets and network programming in general. My goal was to have a better understanding of how low level network communication works using only native Java networking libraries.

The "server" part of the program starts a thread that monitors for any potential connecting clients and will read quotes from a text file (or just provide the current time if the file isn't found).

A "client" simply connects to the server part of the pgoram and receives the data from the server to be used later on.

Something interesting I learned while designing the server part is, you can use a program like Netcat to connect to the server and retrieve the data!

