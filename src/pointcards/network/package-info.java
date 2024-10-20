/**
 * The `network` package contains interfaces and classes that manage network
 * communication
 * between the game client and server. This package includes the following
 * components:
 * 
 * - {@link pointcards.network.INetworkClient}: An interface defining the
 * methods for network clients.
 * - {@link pointcards.network.INetworkServer}: An interface defining the
 * methods for network servers.
 * - {@link pointcards.network.IServerFactory}: An interface for creating server
 * instances.
 * - {@link pointcards.network.tcp.TCPClient}: A class implementing the
 * INetworkClient interface using TCP.
 * - {@link pointcards.network.tcp.TCPServer}: A class implementing the
 * INetworkServer interface using TCP.
 * - {@link pointcards.network.tcp.TCPServerFactory}: A factory class for
 * creating TCPServer instances.
 */
package pointcards.network;