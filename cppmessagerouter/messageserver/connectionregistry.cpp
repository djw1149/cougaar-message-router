/***************************************************************************
                          connectionregistry.cpp  -  description
                             -------------------
    begin                : Wed Jan 28 2004
    copyright            : (C) 2004 by David Craine
    email                : dcraine@infotether.com
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *                                                                         *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

#include "connectionregistry.h"
#include <iostream.h>


ConnectionRegistry::ConnectionRegistry(){
}
ConnectionRegistry::~ConnectionRegistry(){
}

void ConnectionRegistry::registerConnection(ClientConnection *cc, string& name) {
   clientMap[name]=cc;
}
 
ClientConnection * ConnectionRegistry::findConnection(const string& name) {
  ClientConnection* cc = clientMap[name];
  return cc;
  
}

/** No descriptions */
void ConnectionRegistry::deregisterConnection(string &name){
  clientMap.erase(name);
}

/** No descriptions */
string& ConnectionRegistry::listConnections() {
  string* ret = new string();
  
  ConnectionMap::iterator pos;
  for (pos = clientMap.begin(); pos != clientMap.end(); ++pos) {
    *ret += pos->first + "\n";
  }
   
  return *ret;
}
