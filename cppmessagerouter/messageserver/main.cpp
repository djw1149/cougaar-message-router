 /***************************************************************************
                          main.cpp  -  description
                             -------------------
    begin                : Wed Nov 19 09:45:05 EST 2003
    copyright            : (C) 2003 by David Craine
    email                : dcraine@infotether.com
 ***************************************************************************/

 /***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

 #ifdef HAVE_CONFIG_H
 #include <config.h>
 #endif

 #include <iostream.h>
 #include <stdlib.h>
 #include "ServerSocket.h"
 #include "SocketException.h"
 #include "clientconnection.h"
 #include "clientconnectionfactory.h"
 #include <string>
 #include <map.h>
 #include "context.h"

 
 int main(int argc, char *argv[])
 {

 Context::getInstance()->getLogger()->forceLog("Message Router started");
 bool block_read = false;
 if (argc > 1) {
   for (int i=1; i<argc; i++) {
     if (!strcmp(argv[i], "-b")) {
        block_read = true;
        Context::getInstance()->getLogger()->forceLog("block read enabled");
        
      }
      if (!strcmp(argv[i], "-i")) {
        Logger* logger = Context::getInstance()->getLogger();
        logger->setLevel(Logger::LEVEL_INFO);
        logger->enable();
        Context::getInstance()->getLogger()->forceLog("logging enabled at level INFO");
      }
      if (!strcmp(argv[i], "-w")) {
        Logger* logger = Context::getInstance()->getLogger();
        logger->setLevel(Logger::LEVEL_WARN);
        logger->enable();
        Context::getInstance()->getLogger()->forceLog("logging enabled at level WARN");
      }
      if (!strcmp(argv[i], "-d")) {
        Logger* logger = Context::getInstance()->getLogger();
        logger->setLevel(Logger::LEVEL_DEBUG);
        logger->enable();
        Context::getInstance()->getLogger()->forceLog("debugging enabled at LEVEL DEBUG");
      }
      if (!strcmp(argv[i], "-s")) {
        Logger* logger = Context::getInstance()->getLogger();
        logger->setLevel(Logger::LEVEL_SHOUT);
        logger->enable();
        Context::getInstance()->getLogger()->forceLog("debugging enabled at LEVEL SHOUT");
      }
      if (!strcmp(argv[i], "-r")) {
        Context::getInstance()->setAllowDuplicateConnections(false);
        Context::getInstance()->getLogger()->forceLog("disabling duplicate connections");
      }
      if (!strcmp(argv[i], "-e")) {
        Context::getInstance()->enableEavesDropping();
        Context::getInstance()->getLogger()->forceLog("eavesdropping enabled");
      }
      if (!strcmp(argv[i], "-m")) {
        Context::getInstance()->enableErrorMessages();
        Context::getInstance()->getLogger()->forceLog("error messages enabled");
      }
      if (!strcmp(argv[i], "-h")) {
        cout << "Options are:" << endl << flush;
        cout << "   -b Block Read enabled" << endl << flush;
        cout << "   -i Info-level logging enabled" << endl << flush;
        cout << "   -w Warn-level logging enabled" << endl << flush;
        cout << "   -d Debug-level logging enabled" << endl << flush;
        cout << "   -s Shout-level logging enabled" << endl << flush;
        cout << "   -r Refuse duplicate connections" << endl << flush;
        cout << "   -e Enable eavesdropping" << endl << flush;
        cout << "   -m Enable error messages" << endl << flush;
        return EXIT_SUCCESS;
      }     
   }
 }

  try
    {
      // Create the socket
      ServerSocket server ( 6667 );
      //create the connection factory
      ClientConnectionFactory *ccf = new ClientConnectionFactory();
      while ( true )
      {
        ServerSocket *new_sock = new ServerSocket();
        server.accept ( *new_sock );
        //ClientConnection* cc = new ClientConnection(new_sock, false);
        ClientConnection *cc = ccf->createClientConnection(new_sock, false);
        cc->start();
      }
    }
    catch ( SocketException& e )
    {
      Context::getInstance()->getLogger()->forceLog(e.description().c_str());
    }
    
    return EXIT_SUCCESS;
 }
 